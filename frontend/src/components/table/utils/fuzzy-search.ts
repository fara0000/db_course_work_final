import { matchSorter } from 'match-sorter';

fuzzyTextFilterFn.autoRemove = (val: string) => !val;

export function fuzzyTextFilterFn(rows: { values: unknown[] }[], id: string, filterValue: string) {
	return matchSorter(rows, filterValue, { keys: [(row: any) => row.values[id]] });
}
