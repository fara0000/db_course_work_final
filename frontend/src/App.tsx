import React, { useEffect, useState } from 'react';
import { routes } from './core/router/routes';
import { BrowserRouter, Redirect, Route, Switch } from 'react-router-dom';
import { PageHeader } from './components/header/PageHeader';
import { unauthorizedRoutes } from './core/router/unauthorizedRoutes';
import { RedirectWithQuery } from './core/router/redirectWithQuery';
import { Path } from './core/router/paths';
import authStore from './store/auth'
import { LoginPage } from './views/auth/login/LoginPage';
import { RegistrationPage } from './views/auth/registration/RegistrationPage';
import { MainPage } from './views/main/mainPage';
import { LibraryPage } from './views/library/LibraryPage';
import { EventsPage } from './views/event/EventPage';
import { NotFoundPage } from './views/notFoundPage/NotFoundPage';
import { UserType } from './views/auth/types';
import { observer } from 'mobx-react-lite';
import { isBoolean } from 'util';

// TODO: make authentication for user

export const App = observer(() => {
  const jwt = localStorage.getItem('jwt')
  const { isAuthorized } = authStore;
  // const [userData, setUserData] = useState<UserType>({ id: 0, name: '', surname: '', role: '' });

  useEffect(() => {
    if(jwt) {
      authStore.setIsAuthorized(true);
    }
  }, [jwt])

  return isAuthorized ? (
      <BrowserRouter>
        <header style={{ zIndex: 6 }}>
          <PageHeader />
        </header>
        <Switch>
          <Route path="/" exact>
            <RedirectWithQuery to={Path.MAIN} />
          </Route>
          <Route path="/login" exact>
            <RedirectWithQuery to={Path.MAIN} />
          </Route>
          <Route path="/register" exact>
            <RedirectWithQuery to={Path.MAIN} />
          </Route>
          <Route path={Path.MAIN} exact>
            <MainPage />
          </Route>
          <Route path={Path.LIBRARY} exact component={LibraryPage} />

          <Route path={Path.EVENT} exact component={EventsPage} />

          <Route path={Path.NOTFOUND} component={NotFoundPage} />

          <Route path="*">
            <Redirect to={Path.NOTFOUND} />
          </Route>
        </Switch>
      </BrowserRouter>
    ) :
    <BrowserRouter>
      <Switch>
        <Route path={Path.LOGIN} exact>
          <LoginPage />
        </Route>
        <Route path={Path.REGISTER} exact component={RegistrationPage} />
        <Route path="/" exact>
          <RedirectWithQuery to={Path.LOGIN} />
        </Route>
        <Route path={Path.NOTFOUND} component={NotFoundPage} />
        {!jwt &&
          <Route path="*">
            <Redirect to={Path.NOTFOUND} />
          </Route>
        }
      </Switch>
    </BrowserRouter>
});

