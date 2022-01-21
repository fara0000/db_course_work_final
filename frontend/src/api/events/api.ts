import axios from 'axios';
import * as libraryUrls from '../urls';

export const getAllEventsApi = (token: string | null) => {
  return axios.get(libraryUrls.getAllEventsUrl, {
    headers: {
      'Authorization': `Bearer ${token}`
    }
  })
    .then((res) => res.data.events)
    .catch((err) => console.log('getMyBooksApi error:', err))
};

export const getAllEventsFutureApi = (token: string | null) => {
  return axios.get(libraryUrls.getAllEventsFutureUrl, {
    headers: {
      'Authorization': `Bearer ${token}`
    }
  })
    .then((res) => res.data.events)
    .catch((err) => console.log('getAllBooksApi error:', err))
};

export const getAllMeetingsApi = (token: string | null) => {
  return axios.get(libraryUrls.getAllMeetingsUrl, {
    headers: {
      'Authorization': `Bearer ${token}`
    }
  })
    .then((res) => res.data.meetings)
    .catch((err) => console.log('getAvailableBooksApi error:', err))
};

export const createEvent = (data: any, token: any) => {
  return axios.post(libraryUrls.getAllEventsUrl, data,{
    headers: {
      'Authorization': `Bearer ${token}`
    }
  })
    .then((res) => res.data)
    .catch((err) => console.log('borrowBookApi error:', err))
};

export const createMeeting = (data: any, token: any) => {
  return axios.post(libraryUrls.getAllMeetingsUrl + `?meeting={true}`, data, {
    headers: {
      'Authorization': `Bearer ${token}`
    }
  })
    .then((res) => res.data)
    .catch((err) => console.log('returnBookApi error:', err))
};

export const reserveSeatAtMeeting = (meetingId: number, token: string | null) => {
  return axios.post(libraryUrls.getAllEventsUrl + `/${meetingId}/reserveSeat?meeting=true`, {},  {
    headers: {
      'Authorization': `Bearer ${token}`
    }
  })
    .then((res) => res.data)
    .catch((err) => console.log('returnBookApi error:', err))
}
