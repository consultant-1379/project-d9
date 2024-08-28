import { expect, fixture } from '@open-wc/testing';
import ChartLegend from '../../../src/components/chart-legend/chart-legend.js';

describe('ChartLegend Component Tests', () => {
  before(() => {
    ChartLegend.register();
  });

  describe('Basic component setup', () => {
    it('should render <e-chart-legend>', async () => {
      const component = await fixture(
        '<e-chart-legend></e-chart-legend>',
      );
      const headingTag = component.shadowRoot.querySelector('h1');

      expect(
        headingTag.textContent,
        '"Your component markup goes here" was not found',
      ).to.equal('Your component markup goes here');
    });
  });
});
