// @ts-nocheck
import React from 'react';
import {AspectRatio, Box, Heading, Slide, useDisclosure} from "@chakra-ui/react";


export const CardLink = ({imageSrc, header, description, ...props}) => {
  const {isOpen, onOpen, onClose} = useDisclosure();

  return (
    <AspectRatio ratio={16 / 9}>
      <Box
        backgroundImage={`linear-gradient(to bottom, rgba(125, 125, 125, 0.5), rgba(125, 125, 125, 0.5)), url(${imageSrc})`}
        boxShadow="0 1px 3px rgba(0,0,0,0.12), 0 1px 2px rgba(0,0,0,0.24)"
        _hover={{boxShadow: "0 14px 28px rgba(0,0,0,0.25), 0 10px 10px rgba(0,0,0,0.22)"}}
        transition="0.3s"
        outline="0"
        borderWidth='1px'
        position="relative"
        borderRadius='lg' onMouseEnter={onOpen} onMouseLeave={onClose} justifyContent="start !important"
        alignItems="start !important" {...props}>
        <Heading as="h2" fontSize="1.4em" padding={2}
                 backgroundImage="linear-gradient(to bottom, rgba(230, 230, 230, 0.8), rgba(230, 230, 230, 0.8))"
                 width="100%">{header}</Heading>
        <Box width="100%" height="25%" display={isOpen ? "block" : "none"} position="absolute"
             bottom={isOpen ? "0" : "-100%"} transition="all ease 0.3s"
             backgroundImage="linear-gradient(to bottom, rgba(255, 255, 255, 0.55), rgba(255, 255, 255, 0.55))">
          {description}
        </Box>
      </Box>
    </AspectRatio>
  );
}