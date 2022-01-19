import { configure, action, isObservable, makeAutoObservable, observable, toJS } from 'mobx';
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
    this.getMySynagogueInfo()
  }

  @action
  getMySynagogueInfo = async () => {
    const token = localStorage.getItem("jwt")
    this.isLoading = true;
    this.mySynagogue = await synagogueApis.getMySynagogueInfoApi(token);
    const arr = await synagogueApis.getMySynagogueMembersApi(token);
    this.members = [...arr];
    this.isLoading = false;
  }
}

export default new SynagogueStore();
