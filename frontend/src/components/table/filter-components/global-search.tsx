import { Icon, Input, InputGroup, InputLeftElement, InputRightElement } from '@chakra-ui/react';
import React, { FC, useState } from 'react';
import { BiSearch } from 'react-icons/bi';
import { useAsyncDebounce } from 'react-table';

export const GlobalFilter: FC<any> = ({ preGlobalFilteredRows, globalFilter, setGlobalFilter }) => {
	const count = preGlobalFilteredRows.length;
	const [value, setValue] = useState(globalFilter);

	const onChange = useAsyncDebounce((v) => {
		setGlobalFilter(v || undefined);
	}, 200);

	return (
		<InputGroup w="100%" d="flex" alignItems="center" h="50px">
			<InputLeftElement children={<Icon as={BiSearch} color="gray.500" boxSize={4} />} h="100%"/>
			<Input
				// borderRadius="8px"
				variant="flushed"
				placeholder={`Search in ${count} records...`}
				value={value || ''}
				onChange={(e) => {
					setValue(e.target.value);
					onChange(e.target.value);
				}}
			/>
		</InputGroup>
	);
};
