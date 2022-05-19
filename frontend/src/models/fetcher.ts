import instance from "./axios";

function fetcher(url: string) {
  return instance.get(url).then((response) => response.data);
}

export default fetcher;

