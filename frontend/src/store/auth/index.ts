import { configure, action, isObservable, makeAutoObservable, observable, toJS } from 'mobx';
import * as authApis from '../../api/auth/api';
import { RegistrationFormValues, UserType } from '../../views/auth/types';
import { observer } from 'mobx-react-lite';

class AuthStore {
  @observable
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
    this.isAuthorized = true;
    localStorage.setItem("jwt", jwt);
  }

  @action
  setUser = (userData: UserType) => {
    this.isLoading = true;
    const user = toJS({...userData});
    this.user = user;
    console.log(isObservable(this.user), '35')
  }

  @action
  getUser = (user: UserType) => {
    this.user = user;
  }

  @action
  callUser = () => {
    return this.user;
  }

  @action
  getSynagogues = async () => {
    this.isLoading = true;
    this.synagogues = await authApis.getSynagoguesApi();
    this.isLoading = false;
  }
}

export default new AuthStore();
