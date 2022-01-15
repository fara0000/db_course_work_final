import axios from 'axios';
import * as authUrls from './urls';
import { RegistrationFormValues } from '../../views/auth/registration/types';

export const getSynagoguesApi = () => {
  return axios.get(authUrls.getSynagogueUrl)
    .then((res) => res.data)
    .catch((err) => console.log('getSynagoguesApi error:', err))
};

export const saveUserApi = (userData: RegistrationFormValues, synagogueId: number) => {
  return axios.post(authUrls.registerUserUrl, { ...userData, synagogueId: synagogueId }, {
    headers: {
      'content-type': 'application/json'
    },
  })
    .then((res) => res.data)
    .catch((err) => console.log('saveUserApi error:', err))
}