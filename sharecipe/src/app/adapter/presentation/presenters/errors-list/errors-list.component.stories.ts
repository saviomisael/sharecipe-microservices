import {
  Meta,
  StoryObj,
  argsToTemplate,
  moduleMetadata,
} from '@storybook/angular';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { InfrastructureModule } from '../../../infrastructure/infrastructure.module';
import { PresentersModule } from '../../presenters/presenters.module';
import { ErrorsListComponent } from './errors-list.component';

const meta: Meta<ErrorsListComponent> = {
  component: ErrorsListComponent,
  title: 'presenters/ErrorsListComponent',
  decorators: [
    moduleMetadata({
      declarations: [ErrorsListComponent],
      imports: [
        PresentersModule,
        BrowserAnimationsModule,
        InfrastructureModule,
      ],
    }),
  ],
  render: (args: ErrorsListComponent) => ({
    props: {
      ...args,
    },
    template: `<app-create-account-form ${argsToTemplate(
      args
    )}></app-create-account-form>`,
  }),
};

export default meta;

type Story = StoryObj<ErrorsListComponent>;

export const Default: Story = {
  render: () => ({
    props: {
      errors: [
        'Username torvalds already exists.',
        'email provided is invalid.',
      ],
    },
  }),
};
