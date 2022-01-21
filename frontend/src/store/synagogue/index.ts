// @ts-nocheck
import {configure, action, isObservable, makeAutoObservable, observable, toJS, computed} from 'mobx';
import * as synagogueApis from '../../api/synagogue/api';
import * as authTypes from '../../views/auth/types';
import { RegistrationFormValues, UserType } from '../../views/auth/types';
import { observer } from 'mobx-react-lite';


// architectureStyle: "неомавританский"
// id: 1
// library: {id: 2, name: "библиотека", attributes: []}
// name: "Большая хоральная синагога в СПб 2"
// premises: [{id: 1, name: "зал", attributes: [{name: "Бима", description: "Возвышение в центре"}]},…]
// size: 750
// tradition: {id: 1, name: "Ашкеназская",…}

class SynagogueStore {
  mySynagogue = {
    id: 0,
    name: '',
    premises: [],
    architectureStyle: '',
    size: 0,
    tradition: {
      attributes: [],
      description: '',
      id: 0,
      name: '',
    },
  };
  isLoading = false;
  members: authTypes.UserType[] = [];

  constructor() {
    makeAutoObservable(this);
  }

  @action
  getMySynagogueInfo = async () => {
    const token = localStorage.getItem("jwt")
    this.isLoading = true;
    this.mySynagogue = await synagogueApis.getMySynagogueInfoApi(token);
    const memberList = await synagogueApis.getMySynagogueMembersApi(token);
    this.members = [...memberList];
    this.isLoading = false;
  }

  getCommaSeparatedPremises = (premiseIndex: number, attrCount: number) => {
    const premise = this.mySynagogue.premises[premiseIndex];
    if (premise.attributes.length === 0) return "";
    const initial = premise.attributes[0].name;
    // @ts-ignore
    return premise.attributes.splice(1, attrCount).reduce((str, attr) => {
      // @ts-ignore
      return str + `, ${attr.name}`
    }, initial);
  }
}

export default new SynagogueStore();
