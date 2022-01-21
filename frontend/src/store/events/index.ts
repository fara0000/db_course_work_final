import { configure, action, isObservable, makeAutoObservable, observable, toJS } from 'mobx';
import * as eventApis from '../../api/events/api';
import { RegistrationFormValues, UserType } from '../../views/auth/types';
import { observer } from 'mobx-react-lite';

class EventsStore {
  meetings = [];
  events = [];
  futureEvents = [];
  isMeeting = false;
  isFuture = false;
  isEvents = false;

  constructor() {
    makeAutoObservable(this);
  }


  @action
  getMeetings = async () => {
    const jwt = localStorage.getItem('jwt')
    this.meetings = await eventApis.getAllMeetingsApi(jwt);
    this.isMeeting = true;
    this.isEvents = false;
    this.isFuture = false;
  }

  @action
  getEvents = async () => {
    const jwt = localStorage.getItem('jwt')
    const new_events = await eventApis.getAllEventsApi(jwt);
    this.events = new_events;
    this.isEvents = true;
    this.isFuture = false;
    this.isMeeting = false;
  }
  @action
  getFutureEvents = async () => {
    const jwt = localStorage.getItem('jwt')
    const new__future_events = await eventApis.getAllEventsFutureApi(jwt);
    this.futureEvents = new__future_events;
    this.isFuture = true;
    this.isMeeting = false;
    this.isEvents = false;
  }

  @action
  createEvent = async (data: any) => {
    const jwt = localStorage.getItem('jwt')
    const response = await eventApis.createEvent(data, jwt);
    this.getEvents();
  }

  createMeeting = async (data: any) => {
    const jwt = localStorage.getItem('jwt')
    const response = await eventApis.createMeeting(data, jwt);
    this.getMeetings();
  }

  join = async (data: any) => {
    const jwt = localStorage.getItem('jwt')
    const response = await eventApis.reserveSeatAtMeeting(data, jwt);
    this.getMeetings();
  }
}

export default new EventsStore();