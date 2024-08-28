/**
 * Integration tests for <e-project-d9>
 */
import { expect, fixture } from '@open-wc/testing';
import '../../../src/apps/project-d9/project-d9.js';

describe('ProjectD9 Application Tests', () => {
  describe('Basic application setup', () => {
    it('should create a new <e-project-d9>', async () => {
      const element = await fixture('<e-project-d9></e-project-d9>');
      const headingTag = element.shadowRoot.querySelector('h1');

      expect(
        headingTag.textContent,
        '"Your app markup goes here" was not found',
      ).to.equal('Your app markup goes here');
    });
  });
});
