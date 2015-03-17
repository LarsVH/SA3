package softarch.portal.db.json;

import java.util.Date;
import java.util.List;

import softarch.portal.data.RegularData;
import softarch.portal.db.RegularDatabaseInterface;
import softarch.portal.db.sql.DatabaseException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class RegularDatabase extends Database implements RegularDatabaseInterface {

	public RegularDatabase(String dbUrl) {
		super(dbUrl);
	}
	
	public List findRecords(String informationType, String queryString)
			throws DatabaseException {
		throw new NotImplementedException();
	}

	public List findRecordsFrom(String informationType, Date date)
			throws DatabaseException {
		throw new NotImplementedException();
	}

	public void add(RegularData rd) throws DatabaseException {
		throw new NotImplementedException();
	}

	public int getNumberOfRegularRecords(String informationType)
			throws DatabaseException {
		throw new NotImplementedException();
	}

}
