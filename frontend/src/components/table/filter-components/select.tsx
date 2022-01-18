import { Select } from '@chakra-ui/react';
import React, { FC } from 'react';

export const SelectColumnFilter: FC<any> = ({
	column: { filterValue, setFilter, preFilteredRows, id },
}) => {
	const options = React.useMemo(() => {
		const opt: any = new Set();
		preFilteredRows.forEach((row: any) => {
			opt.add(row.values[id]);
		});
		return [...opt.values()];
	}, [id, preFilteredRows]);

	return (
		<Select
			borderRadius="2xl"
			bg="white"
			placeholder="All"
			value={filterValue}
			onChange={(e) => {
				setFilter(e.target.value || undefined);
			}}
		>
			{options.map((option, i) => (
				<option key={i} value={option as string}>
					{option}
				</option>
			))}
		</Select>
	);
};
