import { makeAutoObservable } from 'mobx';
import * as authApis from '../../api/auth/api';
import { RegistrationFormValues } from '../../views/auth/registration/types';
import { AxiosResponse } from 'axios';

class AuthStore {
  synagogues = [];

  constructor() {
    makeAutoObservable(this);
  }

  saveMember(member: RegistrationFormValues, synagogueId: number) {
    return authApis.saveUserApi(member, synagogueId).then(res => res);
  }

  getSynagogues() {
    authApis.getSynagoguesApi()
      .then((res) => this.synagogues = res)
  }
}

export default new AuthStore();
