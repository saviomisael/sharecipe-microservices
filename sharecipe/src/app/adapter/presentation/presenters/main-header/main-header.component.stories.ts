import { Meta, StoryObj, moduleMetadata } from '@storybook/angular';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PresentersModule } from '../presenters.module';
import { MainHeaderComponent } from './main-header.component';

const meta: Meta<MainHeaderComponent> = {
  component: MainHeaderComponent,
  title: 'presenters/MainHeaderComponent',
  decorators: [
    moduleMetadata({
      imports: [BrowserAnimationsModule, PresentersModule],
    }),
  ],
};

export default meta;

type Story = StoryObj<MainHeaderComponent>;

export const Default: Story = {
  render: () => ({
    props: {},
  }),
};
