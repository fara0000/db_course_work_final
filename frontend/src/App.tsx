import React from 'react';
import { LoginPage } from './views/auth/login/LoginPage';
import { RegistrationPage } from './views/auth/registration/RegistrationPage';
import { NotFoundPage } from './views/notFoundPage/NotFoundPage';
import { routes } from './core/router/routes';
import { BrowserRouter } from 'react-router-dom';

export const App = () => {
  return (
    <BrowserRouter>
      {routes}
    </BrowserRouter>
  );
};
