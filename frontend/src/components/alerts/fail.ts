import { createStandaloneToast } from '@chakra-ui/react';

export function errorToast(errorTitle: React.ReactNode, errorDescription?: React.ReactNode) {
  const toast = createStandaloneToast({ defaultOptions: undefined });
  return toast({
    status: 'error',
    isClosable: true,
    title: errorTitle,
    description: errorDescription,
    duration: 3000,
  });
}