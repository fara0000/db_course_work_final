import { Checkbox } from '@chakra-ui/react';
import React from 'react';

export const IndeterminateCheckbox = React.forwardRef(
	({ indeterminate, checked, ...rest }: any, ref: any) => {
		const defaultRef = React.useRef();
		const resolvedRef = ref || defaultRef;

		React.useEffect(() => {
			resolvedRef.current.indeterminate = indeterminate;
		}, [resolvedRef, indeterminate]);

		return <Checkbox ref={resolvedRef} {...rest} isChecked={checked} />;
	}
);
