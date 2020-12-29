/* Helios (TM) 3D Engine (Java): Quaternion Library
* Copyright (C) DeRemee Systems, IXE Electronics LLC
* Portions copyright IXE Electronics LLC, Republic Robotics, FemtoLaunch, FemtoSat, FemtoTrack, Weland
* This work is made available under the Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License.
* To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/4.0/.
/

package Math;

public class Quaternion {
	private float X;
	private float Y;
	private float Z;
	private float W;
	
	public Quaternion() {
		this.W = 0.0f;
		this.X = 0.0f;
		this.Y = 0.0f;
		this.Z = 0.0f;
	}
	
	public Quaternion(float X, float Y, float Z, float W) {
		this.W = W;
		this.X = X;
		this.Y = Y;
		this.Z = Z;
	}
	
	public Quaternion(Vector3 Vector) {
		this.X = Vector.x();
		this.Y = Vector.y();
		this.Z = Vector.z();
	}
	
	public Quaternion(Vector4 Vector) {
		this.W = Vector.w();
		this.X = Vector.x();
		this.Y = Vector.y();
		this.Z = Vector.z();
	}
	
	public Quaternion(Quaternion Quaternion) {
		this.W = Quaternion.w();
		this.X = Quaternion.x();
		this.Y = Quaternion.y();
		this.Z = Quaternion.z();
	}
	
	/*
	 * STATIC METHODS
	 * All computation is performed using these methods, and they all return
	 * a the result in a new quaternion object.
	 */
	
	/**
	 * Computes the quaternion's conjugate, returning the result as a new quaternion
	 * @param A a quaternion
	 * @return a new quaternion
	 */
	public static Quaternion conjugate(Quaternion A) {
		float W = A.w();
		float X = -A.x();
		float Y = -A.y();
		float Z = -A.z();
		return new Quaternion(X, Y, Z, W);
	}
	
	/**
	 * Computes the dot product of two quaternions and returns the result
	 * as a new float
	 * @param A the "left side" quaternion
	 * @param B the "right side" quaternion
	 * @return a new float
	 */
	public static float dot(Quaternion A, Quaternion B) {
		return	A.w() * B.w() +
				A.x() * B.x() +
				A.y() * B.y() +
				A.z() * B.z();
	}
	
	/**
	 * Computes the inverse of the quaternion, returning the result as a new quaternion
	 * @param A a quaternion
	 * @return a new quaternion
	 */
	public static Quaternion inverse(Quaternion A) {
		float InverseLength = 1 / Quaternion.length(A);
		return Quaternion.scale(A, InverseLength);
	}
	
	/**
	 * Computes the quaternion's length, returning the result as a new float
	 * @param A a quaternion
	 * @return a new float
	 */
	public static float length(Quaternion A) {
		return (float) Math.sqrt(A.W * A.W + A.X * A.X + A.Y * A.Y + A.Z * A.Z);
	}
	
	/**
	 * Multiplies two quaternions, returning the result as a new quaternion
	 * @param A the "left side" quaternion
	 * @param B the "right side" quaternion
	 * @return a new quaternion
	 */
	public static Quaternion multiply(Quaternion A, Quaternion B) {
		float W = A.w() * B.w() - A.x() * B.x() - A.y() * B.y() - A.z() * B.z();
		float X = A.x() * B.w() + A.w() * B.x() + A.y() * B.z() - A.z() * B.y();
		float Y = A.y() * B.w() + A.w() * B.y() + A.z() * B.x() - A.x() * B.z();
		float Z = A.z() * B.w() + A.w() * B.z() + A.x() * B.y() - A.y() * B.x();
		return new Quaternion(X, Y, Z, W);
	}
	
	/**
	 * Scales the quaternion, returning the result as a new quaternion
	 * @param A a quaternion
	 * @param Scale a scaling value
	 * @return a new quaternion
	 */
	public static Quaternion scale(Quaternion A, float Scale) {
		float W = A.w() * Scale;
		float X = A.x() * Scale;
		float Y = A.y() * Scale;
		float Z = A.z() * Scale;
		return new Quaternion(X, Y, Z, W);
	}
	
	/**
	 * Normalizes the quaternion, returning the result as a new quaternion
	 * @param A a quaternion
	 * @return a new float;
	 */
	public static Quaternion normalize(Quaternion A) {
		float InverseLength = 1 / Quaternion.length(A);
		float W = A.w() * InverseLength;
		float X = A.x() * InverseLength;
		float Y = A.y() * InverseLength;
		float Z = A.z() * InverseLength;
		return new Quaternion(X, Y, Z, W);
	}
	
	/*
	 * INSTANCE METHODS
	 * All computations are forwarded from these methods to the static methods
	 */
	
	//Accessors
	/**
	 * Copies the quaternion's values to this quaternion
	 * @param A a quaternion
	 */
	public void copy(Quaternion A) {
		A.set(this.X, this.Y, this.Z, this.W);
	}
	
	/**
	 * Returns a new quaternion equal to this quaternion
	 * @return a new quaternion
	 */
	public Quaternion copy() {
		return new Quaternion(this.X, this.Y, this.Z, this.W);
	}
	
	public float w() {
		return this.w();
	}
	
	public float x() {
		return this.x();
	}
	
	public float y() {
		return this.y();
	}
	
	public float z() {
		return this.z();
	}
	
	//Mutators
	/**
	 * Computes the quaternion's conjugate, overwriting the existing values
	 */
	public void conjugate() {
		this.set(Quaternion.conjugate(this));
	}
	
	public float length() {
		return Quaternion.length(this);
	}
	
	/**
	 * Computes the dot product between this quaternion and another,
	 * @param A a quaternion
	 */
	public float dot(Quaternion A) {
		return Quaternion.dot(this, A);
	}
	
	/**
	 * Computes the inverse of this quaternion, overwriting the existing values
	 */
	public void inverse() {
		this.set(Quaternion.inverse(this));
	}
	
	/**
	 * Multiplies this quaternion by another quaternion, overwirting the existing values
	 * @param A
	 */
	public void multiply(Quaternion A) {
		this.set(Quaternion.multiply(this, A));
	}
	
	/**
	 * Normalizes this quaternion, overwriting the existing values
	 */
	public void normalize() {
		this.set(Quaternion.normalize(this));
	}
	
	/**
	 * Scales this quaternion, overwriting the existing values
	 * @param Scale
	 */
	public void scale(float Scale) {
		this.set(Quaternion.scale(this, Scale));
	}
	
	/**
	 * Sets new values for the quaternion
	 * @param X the X component
	 * @param Y the Y component
	 * @param Z the Z component
	 * @param W the W component
	 */
	public void set(float X, float Y, float Z, float W) {
		this.W = W;
		this.X = X;
		this.Y = Y;
		this.Z = Z;
	}
	
	/**
	 * Sets new values for the quaternion
	 * @param A
	 */
	public void set(Quaternion A) {
		this.W = A.w();
		this.X = A.x();
		this.Y = A.y();
		this.Z = A.z();
	}
}
