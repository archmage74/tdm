package tdm.cam.model.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Matrix3x3 {

	public static final Matrix3x3 IDENTITY_MATRIX = new Matrix3x3(new Vector3(1, 0, 0), new Vector3(0, 1, 0), new Vector3(0, 0, 1));

	private Vector3[] rows = new Vector3[3];

	public Matrix3x3() {
		rows[0] = new Vector3(0, 0, 0);
		rows[1] = new Vector3(0, 0, 0);
		rows[2] = new Vector3(0, 0, 0);
	}

	public Matrix3x3(Vector3[] rows) {
		this();
		if (rows.length != 3) {
			throw new RuntimeException("Matrix3x3 must be initialized with 3 rows");
		}
		int i = 0;
		for (Vector3 row : rows) {
			this.rows[i].setX(row.getX());
			this.rows[i].setY(row.getY());
			this.rows[i].setZ(row.getZ());
			i++;
		}
	}

	public Matrix3x3(List<Vector3> rows) {
		this();
		if (rows.size() != 3) {
			throw new RuntimeException("Matrix3x3 must be initialized with 3 rows");
		}
		int i = 0;
		for (Vector3 row : rows) {
			this.rows[i].setX(row.getX());
			this.rows[i].setY(row.getY());
			this.rows[i].setZ(row.getZ());
			i++;
		}
	}

	public Matrix3x3(Vector3 v1, Vector3 v2, Vector3 v3) {
		this(new Vector3[] { v1, v2, v3 });
	}

	public Matrix3x3(Matrix3x3 m) {
		this(m.getRow(0), m.getRow(1), m.getRow(2));
	}

	public Vector3 multiply(Vector3 v) {
		double x = rows[0].multiply(v);
		double y = rows[1].multiply(v);
		double z = rows[2].multiply(v);
		return new Vector3(x, y, z);
	}

	public Matrix3x3 multiply(Matrix3x3 m) {
		ArrayList<Vector3> result = new ArrayList<Vector3>(3);
		for (Vector3 row : rows) {
			double x = row.multiply(m.getColumn(0));
			double y = row.multiply(m.getColumn(1));
			double z = row.multiply(m.getColumn(2));
			result.add(new Vector3(x, y, z));
		}
		return new Matrix3x3(result);
	}

	public double get(int row, int col) {
		return rows[row].get(col);
	}

	public Vector3 getRow(int rowIndex) {
		return new Vector3(rows[rowIndex]);
	}

	public Vector3 getColumn(int columnIndex) {
		double x = rows[0].get(columnIndex);
		double y = rows[1].get(columnIndex);
		double z = rows[2].get(columnIndex);
		return new Vector3(x, y, z);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(rows);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Matrix3x3 other = (Matrix3x3) obj;
		if (!Arrays.equals(rows, other.rows))
			return false;
		return true;
	}

}
