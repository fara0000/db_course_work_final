import React from 'react';
import { Redirect, Route, Switch } from 'react-router-dom';

import { LoginPage } from '../../views/auth/login/LoginPage';
import { Path } from './paths';
import { NotFoundPage } from '../../views/notFoundPage/NotFoundPage';
import { RegistrationPage } from '../../views/auth/registration/RegistrationPage';
import { RedirectWithQuery } from './redirectWithQuery';


export const routes: React.ReactNode = (
  <Switch>
    <Route path={Path.LOGIN} exact component={LoginPage} />

    <Route path={Path.REGISTER} exact component={RegistrationPage} />

    <Route path={Path.NOTFOUND} component={NotFoundPage} />

    <Route path="/" exact>
      <RedirectWithQuery to={Path.LOGIN} />
    </Route>

    <Route path="*">
      <Redirect to={Path.NOTFOUND} />
    </Route>
  </Switch>
);