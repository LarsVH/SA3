package softarch.portal.db.json;

import java.util.List;

import softarch.portal.data.RawData;
import softarch.portal.data.RegularData;
import softarch.portal.db.RawDatabaseInterface;
import softarch.portal.db.sql.DatabaseException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class RawDatabase extends Database implements RawDatabaseInterface {
	
	public RawDatabase(String dbUrl) {
		super(dbUrl);
	}

	public List getRawData() throws DatabaseException {
		throw new NotImplementedException();
	}

	public RawData getRawData(int id) throws DatabaseException {
		throw new NotImplementedException();
	}

	public void addRawData(RegularData regularData) throws DatabaseException {
		throw new NotImplementedException();
	}

	public void deleteRawData(RawData rd) throws DatabaseException {
		throw new NotImplementedException();
	}

	public void updateRawData(RawData rd) throws DatabaseException {
		throw new NotImplementedException();
	}

	public int getNumberOfRawRecords() throws DatabaseException {
		throw new NotImplementedException();
	}

}
