package com.xyzcorp.tdd;

import org.easymock.EasyMock;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.ExpectedException;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DieTest {

    @Test
    @Category(value = UnitTest.class)
    public void testDefaultIs1() {
        Random random = mock(Random.class);
        Die die = new Die(random);
        assertThat(die.getPips()).isEqualTo(1);
    }

    @Test
    @Category(value = UnitTest.class)
    public void testSimpleRollOf4WithStub() {
        //Stub
        //Test Double
        //Collaborator
        Random random = new Random() {
            @Override
            public int nextInt(int bound) {
                return 3;
            }
        };

        //Die is subject under test
        Die die = new Die(random);
        Die rolledDie = die.roll();
        assertThat(rolledDie.getPips()).isEqualTo(4);
    }

    @Test
    @Category(value = UnitTest.class)
    public void testSimpleRollOf4WithMock() {
        Random random = mock(Random.class);

        when(random.nextInt(6)).thenReturn(2, 3);

        //Die is subject under test
        Die die = new Die(random);
        Die rolledDie = die.roll().roll();
        assertThat(rolledDie.getPips()).isEqualTo(4);
    }
    
    @Rule 
    public ExpectedException thrown = ExpectedException.none();
    
    @Test
    @Category(value = UnitTest.class)
    public void testThatRandomCannotBeNull() {
    	   thrown.expect(NullPointerException.class);
    	   thrown.expectMessage("Random cannot be null");
    	   new Die(null);
    }
    
    @Test
    @Category(value = IntegrationTest.class)
    public void testWithARealRandom() {
    	   Die die = new Die(new Random());
    	   for (int i = 0; i < 1000000; i++) {
    		   assertThat(die.roll().getPips()).isGreaterThan(0).isLessThan(7);
    	   }
    }
    
    @Test
    @Category(value = UnitTest.class)
    public void testBUG3012UsingMockito() {
        Random random = mock(Random.class);

        when(random.nextInt(7)).thenReturn(3);

        //Die is subject under test
        Die die = new Die(random);
        Die rolledDie = die.roll();
        assertThat(rolledDie.getPips()).isGreaterThan(0).isLessThan(7);
    }
    
    @Test
    @Category(value = UnitTest.class)
    public void testBUG3012UsingEasyMock() {
        Random random = EasyMock.createMock(Random.class);

        EasyMock.expect(random.nextInt(6)).andReturn(3);
        
        EasyMock.replay(random);

        //Die is subject under test
        Die die = new Die(random);
        Die rolledDie = die.roll();
        assertThat(rolledDie.getPips()).isGreaterThan(0).isLessThan(7);
        
        EasyMock.verify(random);
    }
    
    @Test
    @Category(value = UnitTest.class)
    public void testBUG3012WithAZeroUsingMockito() {
        Random random = mock(Random.class);

        when(random.nextInt(7)).thenReturn(0);

        //Die is subject under test
        Die die = new Die(random);
        Die rolledDie = die.roll();
        assertThat(rolledDie.getPips()).isGreaterThan(0).isLessThan(7);
    }
    
    @Test
    public void testFactoryCreateDie() {
    	     Die die = Die.createStandardDie();
    	     assertThat(die).isNotNull();
    }
}
