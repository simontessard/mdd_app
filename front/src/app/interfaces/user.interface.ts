export interface User {
  id: number;
  email: string;
  lastName: string;
  firstName: string;
  password: string;
  createdAt: Date;
  updatedAt?: Date;
}
