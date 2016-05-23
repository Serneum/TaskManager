class User < ActiveRecord::Base
  acts_as_authentic
  has_many :tasks

  validates :email, presence: true, length: { minimum: 4 }
  validates :crypted_password, presence: true, length: { minimum: 4 }
end
