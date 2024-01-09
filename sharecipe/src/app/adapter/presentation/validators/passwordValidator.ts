import {AbstractControl, ValidatorFn} from '@angular/forms';

const hasUpperCaseLetter = (value: string): boolean => /[A-Z]+/.test(value);

const hasLowerCaseLetter = (value: string): boolean => /[a-z]+/.test(value);

const hasDigit = (value: string): boolean => /[0-9]+/.test(value);

const hasSpecialCharacter = (value: string): boolean =>
  /[^A-Za-z0-9]+/.test(value);

export const passwordValidator: ValidatorFn = (control: AbstractControl) => {
  const {value} = control;
  const isNotValid = {passwordStrength: true};

  if (!value) return isNotValid;

  const isValid = [
    hasUpperCaseLetter,
    hasLowerCaseLetter,
    hasDigit,
    hasSpecialCharacter,
  ].reduce(
    (prevValue: boolean, current: (value: string) => boolean) =>
      prevValue && current(value),
    true
  );

  if (!isValid) return isNotValid;

  return null;
};
