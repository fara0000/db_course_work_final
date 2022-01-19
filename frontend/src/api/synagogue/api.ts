import axios from 'axios';
import * as synagogueUrls from '../urls';

export const getMySynagogueInfoApi = (token: string | null) => {
  return axios.get(synagogueUrls.getMySynagogueInfoUrl, {
    headers: {
      'Authorization': `Bearer ${token}`
    }
  })
    .then((res) => res.data)
    .catch((err) => console.log('getMySynagogueInfoApi error:', err))
};

export const getMySynagogueMembersApi = (token: string | null) => {
  return axios.get(synagogueUrls.getMySynagogueMembersUrl, {
    headers: {
      'Authorization': `Bearer ${token}`,
      'content-type': 'application/json'
    }
  })
    .then((res) => res.data.members)
    .catch((err) => console.log('getMySynagogueMembersApi error:', err))
};