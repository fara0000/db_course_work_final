import React, { useEffect, useState } from 'react';
import { routes } from './core/router/routes';
import { BrowserRouter, Redirect, Route, Switch } from 'react-router-dom';
import { PageHeader } from './components/header/PageHeader';
import { unauthorizedRoutes } from './core/router/unauthorizedRoutes';
import { RedirectWithQuery } from './core/router/redirectWithQuery';
import { Path } from './core/router/paths';
import { LoginPage } from './views/auth/login/LoginPage';
import { RegistrationPage } from './views/auth/registration/RegistrationPage';
import { MainPage } from './views/main/mainPage';
import { LibraryPage } from './views/library/LibraryPage';
import { EventsPage } from './views/event/EventPage';
import { NotFoundPage } from './views/notFoundPage/NotFoundPage';
import { UserType } from './views/auth/types';

// TODO: make authentication for user

export const App = () => {
  const jwt = localStorage.getItem('jwt')
  const [ userData, setUserData ] = useState<UserType>({ id: 0, name: '', surname: '', role: ''});
  const [isAuthorized, setIsAuthorized] = useState(false);

  useEffect(() => {
    console.log(jwt, 'jwt')
    if(jwt) {
      console.log('2')
      setIsAuthorized(true);
    }
  }, [jwt])

  return jwt || isAuthorized ? (
        <BrowserRouter>
          <header style={{ zIndex: 6 }}>
            <PageHeader />
          </header>
          <Switch>
            <Route path="/" exact>
              <RedirectWithQuery to={Path.MAIN} />
            </Route>
            <Route path={Path.LOGIN} exact>
              <LoginPage setUserData={setUserData} />
            </Route>
            <Route path={Path.REGISTER} exact component={RegistrationPage} />
            <Route path={Path.MAIN} exact>
              <MainPage userData={userData}/>
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
        <LoginPage setUserData={setUserData} />
      </Route>
      <Route path={Path.REGISTER} exact component={RegistrationPage} />
      <Route path="/" exact>
        <RedirectWithQuery to={Path.LOGIN} />
      </Route>
      <Route path={Path.NOTFOUND} component={NotFoundPage} />
      <Route path="*">
        <Redirect to={Path.NOTFOUND} />
      </Route>
    </Switch>
  </BrowserRouter>
};
