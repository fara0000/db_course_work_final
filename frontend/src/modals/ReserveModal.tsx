import React from 'react';
import { VStack } from '@chakra-ui/layout';
import {
  Button,
  Modal,
  ModalBody,
  ModalCloseButton,
  ModalContent,
  ModalFooter,
  ModalHeader,
  ModalOverlay,
} from '@chakra-ui/react';
import { Form, Formik } from 'formik';
import { getChakraPortalProps } from '../core/chakra/chakraProvider';
import eventsStore from '../store/events/index';
import { TextInput } from '../components/input/TextInput';

interface ChangePasswordModalProps {
  isOpen: boolean;
  onClose: () => void;
}

export const CreateEvent = ({ isOpen, onClose }: ChangePasswordModalProps) => {
  const {createEvent } = eventsStore

  return (
    <Modal isCentered isOpen={isOpen} onClose={onClose} portalProps={getChakraPortalProps()}>
      <ModalOverlay />
      <Formik
        //new Date().toISOString()
        initialValues={{
          type: '',
          description: '',
          date: '',
          memberIds: [1, 3, 5],
        }}
        onSubmit={async (values, helpers) => {
          const { date } = values;
          const newDate = new Date(date).getTime();

          console.log(newDate);

          await eventsStore.createEvent({...values, date: newDate})
          onClose();
          helpers.resetForm();
        }}
      >
        {({ isSubmitting, dirty, isValid }) => (
          <ModalContent>
            <ModalHeader>Create Event</ModalHeader>
            <ModalCloseButton />
            <ModalBody pb={6}>
              <Form id="change-password-form">
                <VStack spacing="4">
                  <TextInput
                    isRequired
                    name="type"
                    label={"Meeting Type"}
                    helperText={"Set type that you want"}
                  />
                  <TextInput
                    isRequired
                    name="description"
                    label={"Description"}
                  />
                  <TextInput
                    isRequired
                    type="date"
                    name="date"
                    label={"Date"}
                  />
                </VStack>
              </Form>
            </ModalBody>

            <ModalFooter>
              <Button onClick={onClose}>{'Cancel'}</Button>
              <Button
                colorScheme="blue"
                ml="4"
                type="submit"
                form="change-password-form"
                isLoading={isSubmitting}
                isDisabled={!dirty || !isValid}
              >
                {'OK'}
              </Button>
            </ModalFooter>
          </ModalContent>
        )}
      </Formik>
    </Modal>
  );
};
