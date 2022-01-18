export interface RegistrationFormValues {
  name: string;
  surname?: string;
  login: string;
  password: string;
  role: string;
  synagogue: string;
}

export interface LoginFormInitialValues {
  login: string;
  password: string;
}

export type UserType = {
  id: number;
  name: string;
  surname: string;
  role: string;
}