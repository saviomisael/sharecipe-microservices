export interface IDispatcher<T> {
  dispatch(params: T): void;
}
