import { action, makeAutoObservable } from 'mobx';
import * as authApis from '../../api/auth/api';
import { RegistrationFormValues } from '../../views/auth/registration/types';

class AuthStore {
  synagogues = [];
  isLoading = false;

  constructor() {
    makeAutoObservable(this);
  }

  saveMember(member: RegistrationFormValues, synagogueId: number) {
    return authApis.saveUserApi(member, synagogueId).then(res => res);
  }

  @action
  getSynagogues = async () => {
    this.isLoading = true;
    this.synagogues = await authApis.getSynagoguesApi();
    this.isLoading = false;
  }
}

export default new AuthStore();
