import React from 'react';
import {
  Flex,
  Box,
  Checkbox,
  Stack,
  Link,
  Button,
  Heading,
  Text,
  chakra,
} from '@chakra-ui/react';
import { Link as RouterLink, useHistory } from 'react-router-dom';
import { Path } from '../../../core/router/paths';
import { LoginFormInitialValues } from '../types';
import { Form, Formik } from 'formik';
import { TextInput } from '../../../components/TextInput';
import { useBackgroundColor } from '../utils';
import { loginUserApi } from '../../../api/auth/api';
import { errorToast } from '../../../components/alerts/fail';
import authStore from '../../../store/AuthStore';
import { successToast } from '../../../components/alerts/success';

const wikiSynagogueUrl = 'https://en.wikipedia.org/wiki/Synagogue';

// TODO: use yup object for validate input because stars is not a good choice in Login Page

export const LoginPage = ()  => {
  const bg1 = useBackgroundColor('gray.50', 'gray.800');
  const bg2 = useBackgroundColor('white', 'gray.700');
  const history = useHistory();

  return (
    <Formik<LoginFormInitialValues>
      enableReinitialize
      initialValues={{
        login: '',
        password: '',
      }}
      onSubmit={(values, formikHelpers) => {
        if(values.login && values.password) {
          loginUserApi(values).then((res) => {
            console.log(res, 'login response');
            formikHelpers.resetForm();
            return res?.status === 200 ? history.push('/main') : errorToast('Wrong user data, please try again!');
          });
        } else {
          errorToast('Please, enter all needed fields!');
        }
      }}
    >
      {({ isSubmitting, dirty, isValid, values }) => (
        <Flex
          bg={bg1}
          minH={'100vh'}
          align={'center'}
          justify={'center'}
          userSelect={'none'}
        >
          <Stack spacing={8} mx={'auto'} maxW={'lg'} w="450px" pb="45px" px={6}>
            <Stack align={'center'}>
              <Heading fontSize={'4xl'}>Sign in to your account</Heading>
              <Text fontSize={'lg'} color={'gray.600'} mt="0">
                to enjoy all of our <Link color={'blue.400'} href={wikiSynagogueUrl} target="_blank">Synagogue</Link>
              </Text>
            </Stack>
            <Form id="login-page-form" style={{ marginTop: '26px' }}>
              <Box
                rounded={'lg'}
                bg={bg2}
                boxShadow={'lg'}
                p={8}>
                <Stack spacing={4}>
                  <TextInput
                    autoComplete="on"
                    name='login'
                    label='Login'
                  />
                  <TextInput
                    autoComplete="on"
                    name='password'
                    label='Password'
                  />
                  <Stack spacing={4}>
                    <Stack
                      direction={{ base: 'column', sm: 'row' }}
                      align={'start'}
                      justify={'space-between'}>
                      <Checkbox>Remember me</Checkbox>
                      <Link color={'blue.400'}>Forgot password?</Link>
                    </Stack>
                    <Button
                      type="submit"
                      form="login-page-form"
                      loadingText="Submitting"
                      size="lg"
                      bg={'blue.400'}
                      color={'white'}
                      _hover={{
                        bg: 'blue.500',
                      }}
                    >
                      Sign in
                    </Button>
                  </Stack>
                  <Stack>
                    <Text align={'center'}>
                      Don't have an account? <RouterLink to={Path.REGISTER}>
                      <chakra.span color='#4299E1' _hover={{ textDecoration: 'underline' }}>
                        Sing up
                      </chakra.span>
                    </RouterLink>
                    </Text>
                  </Stack>
                </Stack>
              </Box>
            </Form>
          </Stack>
        </Flex>
      )}
    </Formik>
  );
}
