import { Product } from "./product";

export interface Seller{
  id: number;
  inn: number;
  username: string;
  password: string;
  contactInfo: string;
  name: string;
  description: string;
}
