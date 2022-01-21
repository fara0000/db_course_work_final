import { configure, action, isObservable, makeAutoObservable, observable, toJS } from 'mobx';
import * as authApis from '../../api/auth/api';
import { RegistrationFormValues, UserType } from '../../views/auth/types';
import { observer } from 'mobx-react-lite';

class AuthStore {
  user = {
    id: 0,
    name: '',
    role: '',
    surname: '',
  };
  synagogues = [];
  isLoading = false;
  isAuthorized = false;

  constructor() {
    makeAutoObservable(this);
    this.getSynagogues()
  }

  saveMember(member: RegistrationFormValues, synagogueId: number) {
    return authApis.saveUserApi(member, synagogueId).then(res => res);
  }

  @action
  setTokenToLocalStorage = (jwt: string) => {
    localStorage.setItem("jwt", jwt);
    this.isAuthorized = true;
  }

  @action
  deleteTokenFromLocalStorage = () => {
    localStorage.clear();
    this.isAuthorized = false;
  }

  @action
  setUser = (userData: UserType) => {
    console.log(userData, 'user');
    this.isLoading = true;
    this.user = {...userData};
    console.log(isObservable(this.user), '35')
  }

  @action
  getUser = () => {
    return this.user;
  }

  @action
  setIsAuthorized = (bool: boolean) => {
    this.isAuthorized = bool;
  }

  @action
  getSynagogues = async () => {
    this.isLoading = true;
    this.synagogues = await authApis.getSynagoguesApi();
    this.isLoading = false;
  }
}

export default new AuthStore();
