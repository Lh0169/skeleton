export interface UserInfo {
  id: number; username: string; nickname: string;
  email: string; phone: string; avatar: string; status: number;
}
export interface LoginRequest { username: string; password: string }
export interface LoginResponse { token: string; username: string; nickname: string; avatar: string }
