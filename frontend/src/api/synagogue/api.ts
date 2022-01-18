import axios from 'axios';
import * as synagogueUrls from '../urls';

export const getMySynagogueInfoApi = () => {
  return axios.get(synagogueUrls.getMySynagogueInfoUrl)
    .then((res) => res.data)
    .catch((err) => console.log('getMySynagogueInfoApi error:', err))
};

export const getMySynagogueMembersApi = () => {
  return axios.get(synagogueUrls.getMySynagogueMembersUrl)
    .then((res) => res.data)
    .catch((err) => console.log('getMySynagogueMembersApi error:', err))
};