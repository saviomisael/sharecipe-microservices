import {FormGroup} from "@angular/forms";

export const isInvalidField = (form: FormGroup, field: string) => {
  const input = form.get(field);

  if (!input?.dirty && !input?.touched) return false;

  const errors = input?.errors;

  return Boolean(errors);
}
