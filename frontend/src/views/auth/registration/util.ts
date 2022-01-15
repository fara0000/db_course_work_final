export const filterSynagogues = (selectedSynagogue: string, synagogues: any): number => {
  return synagogues.find((item: { name: string }) => item.name === selectedSynagogue).id;
}