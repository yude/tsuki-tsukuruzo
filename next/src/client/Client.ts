import axios from "axios";
import { Status } from "../types/struct";

export class Client {
  public url: string;

  constructor() {
    this.url = "https://tsuki-tsukuruzo.iamtakagi.net/api/";
  }

  get client() {
    return axios.create({
      baseURL: this.url,
      timeout: 1000 * 60,
    });
  }

  async getOAuthUrl() {
    const { data } = await this.client.get(`oauth/getUrl`);
    return data;
  }

  async getStatus() {
    const { data } = await this.client.get<Status>(`status`);
    return data;
  }
}
