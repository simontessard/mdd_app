export interface User {
  id: number;
  email: string;
  name: string;
  lastName: string;
  firstName: string;
  password: string;
  createdAt: Date;
  updatedAt?: Date;
}
