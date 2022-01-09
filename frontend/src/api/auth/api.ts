import axios from 'axios';
import * as authUrls from './urls';

export const getSynagoguesApi = () => {
  return axios.get(authUrls.getSynagogueUrl())
    .then((res) => res.data)
    .catch((err) => console.log('getSynagoguesApi error:', err))
};