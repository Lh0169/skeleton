export interface LoginUser {
  userId: number; username: string; nickname: string;
  token: string; permissions: string[]; roles: string[];
}
