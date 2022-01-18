import {
  Text,
  Icon,
  Menu,
  MenuItem,
  MenuList,
  MenuProps,
  MenuButton,
  IconButton,
} from '@chakra-ui/react';
import React, { FC, ReactNode } from 'react';
import { BsThreeDotsVertical } from 'react-icons/bs';

type ActionsMenuProps = Omit<MenuProps, 'children'> & {
  options: {
    text: ReactNode;
    icon?: JSX.Element;
    onClick?: () => void;
  }[];
};

export const ActionsMenu: FC<ActionsMenuProps> = ({ options, ...props }) => (
  <Menu isLazy placement="auto" size="sm" {...props}>
    <MenuButton
      as={IconButton}
      color="text.medium"
      aria-label="Actions-Menu"
      size="xs"
      variant="unstyled"
      icon={<Icon as={BsThreeDotsVertical} h={6} w={5} />}
      _focus={{
        outline: 0,
      }}
      onClick={(e) => {
        e.stopPropagation();
      }}
      _active={{ color: 'blue.500' }}
    />
    <MenuList
      color="text.medium"
      minW="110px"
      onClick={(e) => e.stopPropagation()}
      boxShadow="0px 0px 3px rgba(0, 14, 35, 0.3)"
      backgroundColor="#FFF"
    >
      {options.filter(Boolean).map(({ text, icon, onClick }, index) => (
        <MenuItem
          px="2"
          py="1"
          key={index}
          icon={icon}
          height="30px"
          display="flex"
          alignItems="center"
          onClick={onClick}
          iconSpacing="2"
          _hover={{ background: 'rgb.700' }}
        >
          <Text color="text.dark" fontSize="13px" lineHeight="19px">
            {text}
          </Text>
        </MenuItem>
      ))}
    </MenuList>
  </Menu>
);
