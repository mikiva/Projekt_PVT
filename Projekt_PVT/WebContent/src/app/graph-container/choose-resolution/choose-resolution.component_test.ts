import {ChooseResolution} from './choose-resolution.component';

describe('ChooseResolution', () => {

    beforeEach(function() {
        this.chooseResolution = new ChooseResolution();
    });

    it('should have hello property', function() {
        expect(this.chooseResolution.resolution[0]).toBe('day');
        expect(this.chooseResolution.resolution[1]).toBe('week');
        expect(this.chooseResolution.resolution[2]).toBe('month');
        expect(this.chooseResolution.resolution[3]).toBe('quarter');
        expect(this.chooseResolution.resolution[4]).toBe('year');
    });

});