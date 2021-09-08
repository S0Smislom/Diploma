import { User } from "./user";

export interface ProductComment{
  id: number;
  user: User;
  productID: number;
  text: string;
  rate: number;
}
