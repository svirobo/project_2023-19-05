package projects.work;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    private Database db;

    @BeforeEach
    void before() {
        db = new Database();
    }

    @Test
    void putItemTest() {
        db.putItem(TestEntityA.class, new TestEntityA(1));
        assertEquals(1, db.mainIndex.size());
        db.putItem(TestEntityA.class, new TestEntityA(2));
        assertEquals(1, db.mainIndex.size());
        db.putItem(TestEntityB.class, new TestEntityA(3));
        assertEquals(2, db.mainIndex.size());
    }

    @Test
    void putItemNullTest() {
        assertThrows(NullPointerException.class, () -> db.putItem(null, new TestEntityA(1)));
    }

    private class TestEntityA implements Identifiable {

        Integer id;

        TestEntityA(Integer id) {
            this.id = id;
        }

        @Override
        public Integer identify() {
            return id;
        }
    }
    private class TestEntityB implements Identifiable {

        Integer id;

        TestEntityB(Integer id) {
            this.id = id;
        }

        @Override
        public Integer identify() {
            return id;
        }
    }
}