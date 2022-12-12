const domainUrl = 'http://localhost:8084';

export const loginUserUrl = `${domainUrl}/login`;
export const registerUserUrl = `${domainUrl}/register`;

export const getMyBooksUrl = `${domainUrl}/books/my`;
export const getAllBooksUrl = `${domainUrl}/books`;
export const getAvailableBooksUrl = `${domainUrl}/books?available=true`;

export const getSynagogueUrl = `${domainUrl}/synagogues`;
export const getMySynagogueInfoUrl = `${domainUrl}/synagogues/my`;
export const getMySynagogueMembersUrl = `${domainUrl}/synagogues/my/members`;

export const getAllEventsUrl = `${domainUrl}/events`;
export const getAllEventsFutureUrl = `${domainUrl}/events?inFuture=true`;
export const getAllMeetingsUrl = `${domainUrl}/events?onlyMeetings=true`;
