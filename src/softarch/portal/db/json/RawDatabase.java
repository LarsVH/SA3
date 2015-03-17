package softarch.portal.db.json;

import java.util.List;

import softarch.portal.data.RawData;
import softarch.portal.data.RegularData;
import softarch.portal.db.RawDatabaseInterface;
import softarch.portal.db.sql.DatabaseException;

public class RawDatabase extends Database implements RawDatabaseInterface {
	
	public RawDatabase(String dbUrl) {
		super(dbUrl);
	}

	public List getRawData() throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	public RawData getRawData(int id) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	public void addRawData(RegularData regularData) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	public void deleteRawData(RawData rd) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	public void updateRawData(RawData rd) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	public int getNumberOfRawRecords() throws DatabaseException {
		// TODO Auto-generated method stub
		return 0;
	}

}
