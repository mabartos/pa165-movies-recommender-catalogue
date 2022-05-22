export default function formatDuration(num: number) {
  const hours = Math.floor(num / 60);
  return hours ? (`${hours} hours ${num % 60} minutes`) : (`${num % 60} minutes`);
}
