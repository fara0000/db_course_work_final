import React, {useEffect} from 'react';
import { Menu } from 'antd';
import SubMenu from 'antd/lib/menu/SubMenu';
import { observer } from 'mobx-react-lite';
import eventsStore from '../../../store/events';

export const LeftSideMenu = observer(() => {
  const  { getMeetings, getEvents, getFutureEvents } = eventsStore;

  useEffect(() => {
    getMeetings();
  }, [])

  return (
    <Menu
      style={{ width: 256 }}
      defaultSelectedKeys={['1']}
      defaultOpenKeys={['sub1']}
      mode={'inline'}
    >
      <Menu.Item key="1" onClick={() => getMeetings()}>
        Meetings
      </Menu.Item>
      <SubMenu
        key="sub1"
        title={
          <span>Events</span>
        }
      >
        <Menu.Item key="2" onClick={() => getEvents()}>All Events</Menu.Item>
        <Menu.Item key="3" onClick={() => getFutureEvents()}>Future Events</Menu.Item>
      </SubMenu>
    </Menu>
  )});