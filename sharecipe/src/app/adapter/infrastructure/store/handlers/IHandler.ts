export interface IHandler<T> {
  handle(params: T): void;
}
