package softarch.portal.db;

import java.util.List;

import softarch.portal.data.RawData;
import softarch.portal.data.RegularData;
import softarch.portal.db.sql.DatabaseException;

public interface RawDatabaseInterface {

	/**
	 * Returns a list of all raw data.
	 */
	public abstract List getRawData() throws DatabaseException;

	/**
	 * Returns a specific raw data object.
	 */
	public abstract RawData getRawData(int id) throws DatabaseException;

	public abstract void addRawData(RegularData regularData)
			throws DatabaseException;

	/**
	 * Deletes a raw data object.
	 */
	public abstract void deleteRawData(RawData rd) throws DatabaseException;

	/**
	 * Updates a raw data object.
	 */
	public abstract void updateRawData(RawData rd) throws DatabaseException;

	/**
	 * Returns the number of records in the raw database.
	 */
	public abstract int getNumberOfRawRecords() throws DatabaseException;

}