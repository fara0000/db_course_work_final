import {
  Button,
  Divider,
  Icon,
  Modal,
  ModalBody,
  ModalCloseButton,
  ModalContent,
  ModalFooter,
  ModalHeader,
  ModalOverlay,
} from '@chakra-ui/react';
import React, { FC, ReactNode } from 'react';
import { IconType } from 'react-icons/lib';
import { getChakraPortalProps } from '../core/chakra/chakraProvider';

interface ModalAction {
  on?: () => void;
  content: ReactNode;
  loading?: boolean;
  disabled?: boolean;
}
interface BaseModalProps {
  body: ReactNode;
  icon?: IconType;
  isOpen: boolean;
  heading: string;
  bgColor?: string;
  onClose: () => void;
  autoClose?: boolean;
  colorScheme?: string;
  primaryAction: ModalAction;
  secondaryAction: ModalAction;
}

export const BaseModal: FC<BaseModalProps> = ({
  body,
  icon,
  isOpen,
  heading,
  bgColor,
  onClose,
  autoClose = true,
  colorScheme,
  primaryAction,
  secondaryAction,
}) => (
  <Modal onClose={onClose} isOpen={isOpen} isCentered portalProps={getChakraPortalProps()}>
    <ModalOverlay />
    <ModalContent>
      <ModalHeader
        bg={bgColor ? bgColor : 'blue.500'}
        color="white"
        display="flex"
        fontSize="md"
        alignItems="center"
      >
        {icon && <Icon as={icon} mr={2} />}
        {heading}
        <ModalCloseButton color="white" mt={'5px'} />
      </ModalHeader>
      <ModalBody fontSize="md">{body}</ModalBody>
      <Divider color="background.medium" />
      <ModalFooter>
        <Button
          isLoading={secondaryAction.loading}
          isDisabled={secondaryAction.disabled}
          onClick={() => {
            secondaryAction.on?.();
            if (autoClose) {
              onClose();
            }
          }}
          mr={3}
          colorScheme="blue"
          variant="outline"
        >
          {secondaryAction.content}
        </Button>
        <Button
          colorScheme={colorScheme ? colorScheme : 'blue'}
          isLoading={primaryAction.loading}
          isDisabled={primaryAction.disabled}
          onClick={() => {
            primaryAction.on?.();
            if (autoClose) {
              onClose();
            }
          }}
          backgroundColor={bgColor ? bgColor : 'none'}
        >
          {primaryAction.content}
        </Button>
      </ModalFooter>
    </ModalContent>
  </Modal>
);
